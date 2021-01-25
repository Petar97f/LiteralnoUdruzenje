import React, { Component, Fragment } from 'react';
import User from '.././user/User';
import Forms from '.././forms/Forms.js';

class SubmitBook extends Component {
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
  


  onSubmit = async (e) => {
    e.preventDefault();
    //first upload files then submit form

       /*try {
        
       let response = await (await fetch(`http://localhost:8081/upload/${this.props.processInstanceId}`, {
          method: 'post',
          headers: {
            //'Accept': 'application/json',
            //'Content-Type': 'application/json',
            'X-Auth-Token': localStorage.getItem("token")
          },
          body: this.state.data
        })).json();
      } catch (err) {
        this.setState({
          errors: err.toString()
        });
      }*/
    
   /* try {
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
          'X-Auth-Token': localStorage.getItem("token")
        },
        body: JSON.stringify({
          dto: returnDto
        })
      })).json();
      console.log(response);
      if (response.status != 'fail') {
        console.log(response);
        console.log(response.formFields);
        if (response.formFields) {
          this.setState({
            formFields: response.formFields,
            taskId: response.taskId
         })
        } else if (response.status === 'success') {
          alert('Book subitted!');
       }
      } else {
        alert('Something went wrong.')
      }
    } catch (err) {
      this.setState({
        errors: err.toString()
      });
    }*/
  }



  render () {
    return (
      <div className="margin-top-page">
        <div className="d-flex flex-row mt-2 ml-3 pt-4">
          <div className="mt-2">
            {this.state.formFields && <Forms formFields={this.state.formFields} onUpdate={(form) => this.setState({form: form})} processInstanceId={this.state.processInstanceId} />}
          </div>
        </div>
        <div className="d-flex flex-row mt-2 ml-3">
        {this.state.formFields ?
          <button className="btn btn-primary" type="button" onClick={this.onSubmit}>Submit</button>
        : <p>Already submited works</p>
        }
        </div>
      </div>
    );
  }
  
}

export default SubmitBook;
