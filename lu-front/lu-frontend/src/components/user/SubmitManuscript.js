import React, {Component} from "react";
import {withRouter} from "react-router-dom";
import {Form, Modal} from "react-bootstrap";
import Forms from "../forms/Forms";

class SubmitManuscript extends Component{
    constructor(props) {
        super(props);
        this.state = {
            formFields: [],
            form: {},
            data: []
        }
    }

    async componentDidMount () {
        try {
            let response = await (await fetch('http://localhost:8081/getTask', {
                method: 'get',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'X-Auth-Token': localStorage.getItem("token")
                },
            })).json();
            console.log(response)
            this.setState({
                formFields: response.formFields,
                taskId: response.taskId,
                processInstanceId: response.processInstanceId
            })

        } catch (err) {
            this.setState({
                errors: err.toString()
            });
        }
    }

    onSubmit = async () => {
        try {
            let returnDto = [];
            returnDto = Object.keys(this.state.form).map(value => {
                let res = {};
                if (this.state.form[value]) {
                    res.fieldId = value;
                    res.fieldValue = this.state.form[value].toString();
                    return res;
                }
                res.fieldId = value;
                res.fieldValue = this.state.form[value];
                return res;
            });
            console.log(returnDto)
            let response = await (await fetch(`http://localhost:8081/submitForm/${this.state.taskId}`, {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    dto: returnDto
                })
            })).json();
            if (response.status != 'fail') {
                console.log(response);
                console.log(response.formFields);
                if (response.formFields) {
                    this.setState({
                        formFields: response.formFields,
                        taskId: response.taskId
                    })
                } else if (response.status === 'success') {
                    alert('Submitted');
                    this.setState({
                        formFields: [],
                        form: {},
                    })
                }
            } else {
                alert('Something went wrong.');
                this.props.onClose();
            }
        } catch (err) {
            this.setState({
                errors: err.toString()
            });
        }
    }




    render () {
        return (
            <div>
                <Modal show={this.props.show} onHide={this.props.onClose} style={{ paddingTop: '65px' }}>
                    <Form className="needs-validation" role="form"  onSubmit={this.onSubmit}>
                        <Modal.Header closeButton>
                            <Modal.Title>
                                <label>Give review</label>
                            </Modal.Title>
                        </Modal.Header>
                        <Modal.Body style={{ maxHeight: 'calc(100vh - 30px - 30px - 75px - 57px - 60px - 16px)', overflowY: 'auto' }}>
                            {this.state.formFields && <Forms formFields={this.state.formFields} onUpdate={(form) => this.setState({form: form})} />}
                        </Modal.Body>
                        <Modal.Footer>
                            <button className="btn btn-primary" type="submit">Send</button>
                            <button className="btn btn-primary"  onClick={this.props.onClose}>Cancel</button>
                        </Modal.Footer>
                    </Form>
                </Modal>
            </div>
        );
    }
}

export default withRouter(SubmitManuscript);