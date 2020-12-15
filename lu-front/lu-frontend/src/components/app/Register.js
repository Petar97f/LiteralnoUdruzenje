import React, { Component, Fragment} from 'react';
import { Form, Dropdown, Modal, InputGroup } from 'react-bootstrap';
import { withRouter } from 'react-router-dom';
import { Link } from 'react-router-dom';

class Register extends Component {
	constructor(props) {
		super(props);
		this.state = {
      user: {},
			firstName: '',
			lastName: '',
			password: '',
			confirmPassword: '',
      genres: [],
      selectedGenres: [],
			isBeta: false,
      formFields: [],
      returnDto: [],
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

  onInputChange = (name, value) => {
    console.log(name, value);
    let user = {...this.state.user};
    user[name] = value;
    this.setState({
      user: user
    });
  }

  onSubmitRegister = async () => {
    try {
      let returnDto = [];
      let formFields = this.state.formFields.filter(item => {
        if (item.id !== "genres") {
          return item;
        } 
      })
      returnDto = formFields.map( item => {
          let res = {};
          if (item.id !== "genres") {
            res.fieldId = item.id;
            res.fieldValue = this.state.user[item.id];
            return res;
          } 
          
      }) 
      console.log(returnDto);
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
      })).json();
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
					<Modal.Header closeButton>
						<Modal.Title>
							<label>Register</label>
						</Modal.Title>
					</Modal.Header>
					<Modal.Body style={{ maxHeight: 'calc(100vh - 30px - 30px - 75px - 57px - 60px - 16px)', overflowY: 'auto' }}>
						<Form>
							{this.state.formFields && this.state.formFields.map(item => {
                if (item.type.name === 'enum') {
                  return (
                    <Form.Group>
                      <Form.Label className="font-weight-bold">Select Genres:</Form.Label>
                      {Object.keys(item.type.values).map(value =>{
                        console.log(item.type.values[value])
                        return (
                          <Form.Check type="checkbox" value={this.state.selectedGenres.includes(value)} label={item.type.values[value]} onClick={e => this.onGenreChange(e, value)}/>
                        )
                      })}
                    </Form.Group>
                  )
                }
								return(
									<Form.Group>
										<Form.Label className="font-weight-bold">{item.label}</Form.Label>
										<Form.Control type={item.type.name} placeholder={item.label} value={this.state.user[item.id]} onChange={e => this.onInputChange(item.id, e.target.value)} />
									</Form.Group>
								)
							})

							}
						</Form>
				
					</Modal.Body>
					<Modal.Footer>
						<button className="btn btn-primary" onClick={this.onSubmitRegister}>Register</button>
						<button className="btn btn-primary"  onClick={this.props.onClose}>Cancel</button>
					</Modal.Footer>
				</Modal>
		</div>
    );
  }
}

export default withRouter(Register);