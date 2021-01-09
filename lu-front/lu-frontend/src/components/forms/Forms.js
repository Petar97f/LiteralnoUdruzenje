import React, { Component} from 'react';
import { Form } from 'react-bootstrap';

class Forms extends Component {
	constructor(props) {
		super(props);
		this.state = {
      form: {
      }
    }
  }

  componentDidMount () { 
  
  }

  onSelectChange = (e, id, selectedForm) => {
    let form = {...this.state.form};
    if (e.target.checked) {
      if (!this.state.form[selectedForm]) {
        form[selectedForm] = [id];
        this.setState({
          form: form
        });
      } else if (!this.state.form[selectedForm].includes(id)) {
        form[selectedForm] = this.state.form[selectedForm].concat([id]); 
        this.setState({
          form: form
        });
      } 
    } else {
      if (this.state.form[selectedForm].includes(id)) {
        form[selectedForm] = this.state.form[selectedForm].filter(item => item !== id )
        this.setState({
          form: form
        });
      }
    }
    this.props.onUpdate(form);
  }

  onInputChange = (name, value) => {
    let form = {...this.state.form};
    form[name] = value;
    this.setState({
      form: form
    });
    this.props.onUpdate(form);
  }

  render () {
    return (
      <div>
        {this.props.formFields && this.props.formFields.map(item => { 
          if (item.type.name === 'enum') {
            if (item.properties['multiselect'] !== undefined) {
              return (
                <Form.Group key={item.id}>
                  <Form.Label className="font-weight-bold">Select {item.label}:</Form.Label>
                  {Object.keys(item.type.values).map(value => {
                    return (
                      <Form.Check className={this.state.form[item.id] && this.state.form[item.id].length > 0 ? "is-valid" : "is-invalid"} key={value} type="checkbox" label={item.type.values[value]} onClick={e => this.onSelectChange(e, value, item.id)}/>
                    )
                  })}
                  <Form.Control.Feedback type="invalid">
                    Please select at least one.
                  </Form.Control.Feedback>
                </Form.Group>
              );
            }
          } else if (item.type.name === 'string') {
            if (item.properties['password'] !== undefined) {
              return (
                <Form.Group key={item.id}>
                  <Form.Label className="font-weight-bold">{item.label}</Form.Label>
                  <Form.Control type={item.properties.password} placeholder={item.label} value={this.state.form[item.id] ? this.state.form[item.id] : ''} onChange={e => this.onInputChange(item.id, e.target.value)} required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]}/>
                </Form.Group>
              );
            } else if (item.properties['email'] !== undefined) {
              return (
                <Form.Group key={item.id}>
                  <Form.Label className="font-weight-bold">{item.label}</Form.Label>
                  <Form.Control id="validationDefault02" type={item.properties.email} placeholder={item.label} value={this.state.form[item.id] ? this.state.form[item.id] : ''} onChange={e => this.onInputChange(item.id, e.target.value)} aria-describedby="inputGroupPrepend2" required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]}/>
                </Form.Group>
              )
            } else {
              return (
                <Form.Group key={item.id}>
                  <Form.Label className="font-weight-bold">{item.label}</Form.Label>
                  <Form.Control type={item.type.name} placeholder={item.label} value={this.state.form[item.id] ? this.state.form[item.id] : ''} onChange={e => this.onInputChange(item.id, e.target.value)} required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]}/>
                </Form.Group>
              )
            }
          } else if (item.type.name === 'boolean') {
            return (
              <Form.Group key={item.id}>
                <Form.Check type="checkbox" label={item.label} value={this.state.form[item.id] ? this.state.form[item.id] : false} onChange={e => this.onInputChange(item.id, e.target.checked)} />
              </Form.Group>
            ); 
          }
        })}
      </div>
    );
  }

}

export default Forms;