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
      country: '',
      genres: [],
      selectedGenres: [],
			isBeta: false,
			formFields: []
		}
	}

  async componentDidMount () {
		/*try {
      let response = await (await fetch('http://localhost:8081/getGenres', {
        method: 'get',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json',
					'Access-Control-Allow-Origin': '*',
        },
      })).json();
      console.log(response)
      this.setState({
        genres: response
      })

    } catch (err) {
      this.setState({
        errors: err.toString()
      });
		}*/
		try {
      let response = await (await fetch('http://localhost:8081/getFormRegister', {
        method: 'get',
        headers: {
          'Accept': 'application/json',
					'Content-Type': 'application/json',
					'Access-Control-Allow-Origin': '*',
        },
      })).json();
      console.log(response)
      this.setState({
 				formFields: response.formFields
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
  
  onGenreChange = (e, id) => {
    if (e.target.checked) {
      if (!this.state.selectedGenres.includes(id)) {
        this.setState({
          selectedGenres: this.state.selectedGenres.concat([id])
        })
      } 
    } else {
      if (this.state.selectedGenres.includes(id)) {
        this.setState({
          selectedGenres: this.state.selectedGenres.filter(item => item !== id )
        })
      }
    }
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
							{this.state.formFields && this.state.formFields.map(item => {
								return(
									<Form.Group>
										<Form.Label className="font-weight-bold">{item.label}</Form.Label>
										<Form.Control type={item.type.name} placeholder={item.label} value={this.state.firstName} />
									</Form.Group>
								)
							})

							}
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