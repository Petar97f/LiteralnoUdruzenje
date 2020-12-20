import React, { Component} from 'react';
import { Form, Modal } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { withRouter } from 'react-router-dom';
import User from '.././user/User';
import jwt from 'jwt-decode' 

class Login extends Component {
	constructor(props) {
		super(props);
		this.state = {
			email: '',
			password: '',
			errors: [],
			error: ''
		}
	}

	onLogin = async (e) => {
		e.preventDefault();
		console.log("clicked button login");
		try {
      let response = await (await fetch('http://localhost:8081/login', {
        method: 'post',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json',
        },
        body: JSON.stringify({
					email: this.state.email,
					password: this.state.password
        })
      })).text();
			console.log(response);
			localStorage.setItem('token', response);
			let user = jwt(response);
			console.log(user);
			console.log("here");
			if (response != 'fail') {
				User.isLoggedIn = true;
				User.name = user.sub.name;
				User.surname = user.sub.surname;
				User.email = user.sub.email;
				User.city= user.sub.city;
				User.country = user.sub.country;
				User.role = user.roles[0].authority;
			} else {
				this.setState({
					error: 'Invalid username or password'
				});
			}
			this.props.history.push('/user');
    } catch (err) {
      this.setState({
        errors: err.toString()
      });
    }
	}
	
	onRegister = (e) => {
		e.preventDefault();
		this.props.onRegister();
	}

  render () {
    return (
			<div>
				<Modal show={this.props.show} onHide={this.props.onClose} style={{ paddingTop: '65px' }}>
					<Modal.Header closeButton>
						<Modal.Title>
							<label>Login</label>
						</Modal.Title>
					</Modal.Header>
					<Modal.Body style={{ maxHeight: 'calc(100vh - 30px - 30px - 75px - 57px - 60px - 16px)', overflowY: 'auto' }}>
					<Form>
						<Form.Group>
							<Form.Label>Email address</Form.Label>
							<Form.Control type="email" placeholder="Enter email" value={this.state.email} onChange={e => this.setState({email: e.target.value})} />
						</Form.Group>
						<Form.Group controlId="formBasicPassword">
							<Form.Label>Password</Form.Label>
							<Form.Control type="password" placeholder="Password" value={this.state.password} onChange={e => this.setState({password: e.target.value})}  />
						</Form.Group>
					</Form>
					<div>
						<a href="!#" className="mr-2" onClick={this.onRegister}>Register</a>
						<span className="text-muted">|</span>
						<a href="#!" className="ml-2" onClick={e => e.preventDefault()}>Lost your password?</a>
					</div>	
				</Modal.Body>
				<Modal.Footer>
					<button className="btn btn-primary" onClick={this.onLogin}>Login</button>
					<button className="btn btn-primary"  onClick={this.props.onClose}>Cancel</button>
				</Modal.Footer>
				</Modal>
		</div>
    );
  }
}

export default withRouter(Login);
