import React, { Component, Fragment } from 'react';
import User from '.././user/User';
import Forms from '.././forms/Forms.js';
import { Form, Dropdown, Modal, InputGroup } from 'react-bootstrap';

class SubmitBook extends Component {
  constructor(props) {
    super(props);
    this.state = {
      formFields: [],
      form: {},
      data: [], 
      upoladActive: false
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
  

  onSubmit = async (e) => {
    e.preventDefault();
  }



  render () {
    return (
      <div className="margin-top-page">
        <Form className="needs-validation" role="form"  onSubmit={this.onSubmit}>
        <div className="d-flex flex-row mt-2 ml-3 pt-4">
          <div className="mt-2">
            {this.state.formFields && <Forms formFields={this.state.formFields} onUpdate={(form) => this.setState({form: form})}  processInstanceId={this.state.processInstanceId} taskId={this.state.taskId} />}
          </div>
        </div>
        <div className="d-flex flex-row mt-2 ml-3">
        {this.state.formFields ?
          <button className="btn btn-primary" type="submit" /*onClick={this.onSubmit}*/>Submit</button>
        : <p>Already submited works</p>
        }
        </div>
        </Form>
      </div>
    );
  }
  
}

export default SubmitBook;
