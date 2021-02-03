import React, { Component, Fragment} from 'react';
import { Form, Dropdown, Modal, InputGroup } from 'react-bootstrap';
import { withRouter } from 'react-router-dom';
import { Link } from 'react-router-dom';
import Forms from '.././forms/Forms.js';


class Publish extends Component{
    constructor(props) {
        super(props);
        this.state = {
            formFields: [],
            form: {}
        }
    }

    async componentDidMount () {
        try {
            let response = await (await fetch('http://localhost:8081/start/publishing', {
                method: 'get',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
            })).json();
            this.setState({
                formFields: response.formFields,
                taskId: response.taskId
            })

        } catch (err) {
            this.setState({
                errors: err.toString()
            });
        }
    }

    onLogin = (e) => {
        e.preventDefault();
        this.props.onLogin();
    }

    onSubmitRegister = async (e) => {
        e.preventDefault();
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
            let response = await (await fetch(`http://localhost:8081/submitForm/${this.state.taskId}`, {
                method: 'post',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*',
                },
                body: JSON.stringify({
                    dto: returnDto
                })
            })).text();
            if (response != 'fail') {
                alert('Registration successful, we will send you email to confirm registration');
                this.props.onClose();
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
                    <Form className="needs-validation" role="form"  onSubmit={this.onSubmitRegister}>
                        <Modal.Header closeButton>
                            <Modal.Title>
                                <label>Register</label>
                            </Modal.Title>
                        </Modal.Header>
                        <Modal.Body style={{ maxHeight: 'calc(100vh - 30px - 30px - 75px - 57px - 60px - 16px)', overflowY: 'auto' }}>
                            {this.state.formFields && <Forms formFields={this.state.formFields} onUpdate={(form) => this.setState({form: form})} />}
                        </Modal.Body>
                        <Modal.Footer>
                            <button className="btn btn-primary" type="submit">Register</button>
                            <button className="btn btn-primary"  onClick={this.props.onClose}>Cancel</button>
                        </Modal.Footer>
                    </Form>
                </Modal>
            </div>
        );
    }
}

export default withRouter(Publish);