import React, { Component, Fragment } from 'react';
import User from '.././user/User';
import Publish from '../app/Publish';
import ReadManuscript from "../app/ReadManuscript";
import SubmitBook from "./SubmitBook";
import SubmitManuscript from "./SubmitManuscript";

class UserPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      merchatId: '1234',
      showPublisher: false,
      showManuscript: false,
      showSubmit: false
    }
  }

 
	async componentDidMount () {
		try {
      let response = await (await fetch('http://localhost:8081/getBooks', {
        method: 'get',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json',
          'X-Auth-Token': localStorage.getItem("token")
        },
      })).json();
      console.log(response)
      this.setState({
        books: response
      })

    } catch (err) {
      this.setState({
        errors: err.toString()
      });
    }
	}

  openPublisher = (e) => {
    e.preventDefault();
    this.setState({
      showPublisher: true
    })
  }

  openSubmit = (e) => {
    e.preventDefault();
    this.setState({
      showSubmit: true
    })
  }

  openManuscript = (e) => {
    e.preventDefault();
    this.setState({
      showManuscript: true
    })
  }
  
  onBuy = async () => {
    //now we need to redirect on new app
    try {
      let response = await (await fetch(`http://localhost:8081/buy/${this.state.merchantId}`, {
        method: 'post',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json',
					'X-Auth-Token': localStorage.getItem("token")
        },
        body: JSON.stringify({
       
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
      <div className="margin-top-page">
        <div className="d-flex flex-row mt-2 ml-3 pt-4">
          <div className="mt-2">
            {this.state.books && this.state.books.map(item => {
              return (
                <div style={{border: '1px solid grey', padding: '12px', margin: '5px'}}>
                  <p><label>Book name: {item.name}</label></p>
                  <a className="text-decoration-none" target="_blank" href={`http://localhost:3005/${item.publisherId}/${item.price}`}><button className="btn btn-primary" style={{width: '150px'}}>Buy</button></a>
                </div>
              )
            })}
          </div>
        </div>
        <div className="d-flex flex-row mt-2 ml-3">
          <button className="btn btn-primary" onClick={this.openPublisher}>Submit book</button>
          <button className="btn btn-primary" onClick={this.openSubmit}>Save pdf book</button>
          <button className="btn btn-primary" onClick={this.openManuscript}>Save manuscript</button>
        </div>
        {this.state.showPublisher && <Publish show={this.state.showPublisher} onClose={e => this.setState({showPublisher: false})}/>}
        {this.state.showSubmit && <SubmitBook show={this.state.showSubmit} onClose={e => this.setState({showSubmit: false})}/>}
        {this.state.showManuscript && <SubmitManuscript show={this.state.showManuscript} onClose={e => this.setState({showManuscript: false})}/>}
      </div>
    );
  }
  
}
export default UserPage;
