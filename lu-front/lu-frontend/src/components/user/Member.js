import React, { Component, Fragment } from 'react';
import User from '.././user/User';
import Forms from '.././forms/Forms.js';

class Member extends Component {
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
          alert('Opinion given');
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
      <div className="margin-top-page">
        <div className="d-flex flex-row ml-3 pt-4">
          <div className="mt-2">
            Give opinion:
            <div className="mt-2">
              {this.state.formFields && <Forms formFields={this.state.formFields} onUpdate={(form) => this.setState({form: form})} processInstanceId={this.state.processInstanceId} taskId={this.state.taskId} />}
            </div>
            <div>
            {this.state.formFields ?
                <button className="btn btn-primary" type="button" onClick={this.onSubmit}>Submit</button>
                :
                <p>-No new works</p>
            } 
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Member;
