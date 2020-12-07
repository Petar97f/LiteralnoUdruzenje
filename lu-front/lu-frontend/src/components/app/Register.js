import React, { Component, Fragment} from 'react';
import { Form, Dropdown, Modal, InputGroup } from 'react-bootstrap';
import { withRouter } from 'react-router-dom';
import { Link } from 'react-router-dom';

class Register extends Component {
	constructor(props) {
		super(props);
		this.state = {
			firstName: '',
			lastName: '',
			password: '',
			confirmPassword: '',
			email: '',
			city: '',
			country: ''
		}
	}

	onLogin = (e) => {
		e.preventDefault();
		this.props.onLogin();
	}

  render () {
    return (
			<div>
				<Modal show={this.props.show} onHide={this.props.onClose} style={{ paddingTop: '65px' }}>
					<Modal.Header closeButton>
						<Modal.Title>
							<label>Register</label>
						</Modal.Title>
					</Modal.Header>
					<Modal.Body style={{ maxHeight: 'calc(100vh - 30px - 30px - 75px - 57px - 60px - 16px)', overflowY: 'auto' }}>
					<Form>
						<Form.Group>
							<Form.Label>First Name</Form.Label>
							<Form.Control type="email" placeholder="First Name *" value={this.state.firstName} onChange={e => this.setState({firstName: e.target.value})} />
						</Form.Group>
						<Form.Group>
							<Form.Label>Last Name</Form.Label>
							<Form.Control type="email" placeholder="Last Name *" value={this.state.lastName} onChange={e => this.setState({lastName: e.target.value})} />
						</Form.Group>
						<Form.Group>
							<Form.Label>Email address</Form.Label>
							<Form.Control type="email" placeholder="Your email *" value={this.state.email} onChange={e => this.setState({email: e.target.value})} />
						</Form.Group>
						<Form.Group >
							<Form.Label>Password</Form.Label>
							<Form.Control type="password" placeholder="Password *" value={this.state.password} onChange={e => this.setState({password: e.target.value})}  />
						</Form.Group>
						<Form.Group>
							<Form.Label>Confirm Password</Form.Label>
							<Form.Control type="password" placeholder="Confirm Password *" value={this.state.confirmPassword} onChange={e => this.setState({confirmPassword: e.target.value})}  />
						</Form.Group>
						<Form.Group>
							<Form.Label>City</Form.Label>
							<Form.Control type="text" placeholder="City *" value={this.state.city} onChange={e => this.setState({city: e.target.value})}  />
						</Form.Group>
						<Form.Group>
							<Form.Label>Country</Form.Label>
							<Form.Control type="text" placeholder="Country *" value={this.state.country} onChange={e => this.setState({country: e.target.value})}  />
						</Form.Group>
						<div>
							Already registered<a href="#" className="ml-2" onClick={this.onLogin}>Login</a>
						</div>	
					</Form>
					</Modal.Body>
					<Modal.Footer>
						<button className="btn btn-primary">Register</button>
						<button className="btn btn-primary"  onClick={this.props.onClose}>Cancel</button>
					</Modal.Footer>
				</Modal>
		</div>
    );
  }
}

export default withRouter(Register);