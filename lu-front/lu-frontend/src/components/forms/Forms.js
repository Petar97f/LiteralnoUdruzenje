import React, { Component} from 'react';
import { Form } from 'react-bootstrap';
import axios from 'axios';

const sleep = (seconds) => new Promise((resolve, reject) => setTimeout(resolve, seconds * 1000));

class Forms extends Component {
	constructor(props) {
		super(props);
		this.state = {
      form: {
      },
      errors: ''
    }
  }

  componentDidMount = () => { 
  
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

  onInputChange = (e, name, value) => {
    let form = {...this.state.form};
    form[name] = value;
    this.setState({
      form: form
    });
    this.props.onUpdate(form);
  }
  onRadioButton = (e, id, value, selectName) => {
    let name = document.getElementsByName(selectName);
    let form = {...this.state.form};
    for (let i = 0; i < name.length; i++) {
      if (name[i].checked) {
        form[name[i].id] = name[i].checked.toString();
      } else {
        form[name[i].id] = name[i].checked.toString();
      }
    }
    this.setState({
      form: form
    });
    this.props.onUpdate(form);
  }

  
  onFileUpload = async (e, name, minLength) => {
    try {
      let form = {...this.state.form};
      let files = Array.from(e.target.files)
      if (minLength && Number(minLength) > files.length) {
        alert("Please submit more files");
        return;
      }
      let listFilenames = [];
      let data = new FormData();

      for (let i = 0; i < files.length; i++) {
        let file = files[i];
        let filename = files[i].name;
        listFilenames.push(filename)
        //let ext = filename.split('.')[filename.split('.').length - 1];
        let blob = file.slice(0, file.size, file.type); 
        file = new File([blob], `${filename}`, {type: file.type});
        console.log(filename)
        data.append(`files`, file, filename);
      }

      let response = await (await fetch(`http://localhost:8081/upload/${this.props.processInstanceId}/${this.props.taskId}`, {
        method: 'post',
        headers: {
          //'Accept': 'application/json',
          //'Content-Type': 'application/json',
          'X-Auth-Token': localStorage.getItem("token")
        },
        body: data
      })).json();
      form[name] = listFilenames;
      this.setState({
        form: form
      });
      this.props.onUpdate(form);
  } catch (err) {
    this.setState({
      errors: err.toString()
    });
  }
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
                  <Form.Control type={item.properties.password} placeholder={item.label} value={this.state.form[item.id] ? this.state.form[item.id] : ''} onChange={e => this.onInputChange(e, item.id, e.target.value)} required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]}/>
                </Form.Group>
              );
            } else if (item.properties['email'] !== undefined) {
              return (
                <Form.Group key={item.id}>
                  <Form.Label className="font-weight-bold">{item.label}</Form.Label>
                  <Form.Control id="validationDefault02" type={item.properties.email} placeholder={item.label} value={this.state.form[item.id] ? this.state.form[item.id] : ''} onChange={e => this.onInputChange(e, item.id, e.target.value)} aria-describedby="inputGroupPrepend2" required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]}/>
                </Form.Group>
              )
            } else if (item.properties['file'] !== undefined) {
              return (
                <Form.Group key={item.id}>
                  <Form.Label className="font-weight-bold">{item.label}</Form.Label>
                  <Form.Control id="validationDefault02" type={item.properties.file} placeholder={item.label} onChange={e => this.onFileUpload(e, item.id, item.properties.minLength)} aria-describedby="inputGroupPrepend2" required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]} multiple={item.properties.multiple ? true : false}/>
                </Form.Group>
              )
            } else if (item.properties['textarea'] !== undefined) { 
              return (
                <Form.Group key={item.id}>
                  <Form.Label className="font-weight-bold">{item.label}</Form.Label>
                  <Form.Control as="textarea" cols={item.properties['cols']} rows={item.properties['rows']} placeholder={item.label} value={this.state.form[item.id] ? this.state.form[item.id] : ''} onChange={e => this.onInputChange(e, item.id, e.target.value)} required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]}/>
                  <Form.Control.Feedback type="invalid">
                    Dont leave empty.
                  </Form.Control.Feedback>
                </Form.Group>
              )
            } else if (item.properties['readonly'] !== undefined) {
              if (item.properties['download'] !== undefined) {
                let links = item.value.value.split(',');
                return links.map((link, i ) => {
                  let name = link.split('/');
                  return (
                    <Form.Group key={i}>
                      <Form.Label className="font-weight-bold">Download:</Form.Label>
                      <a className="btn btn-link mr-auto" href={link} target="_blank" rel='noopener noreferrer'>{name[name.length-1]}</a>
                    </Form.Group>
                  )
                }) 
                
              } else {
                return (
                  <Form.Group key={item.id}>
                    <Form.Label className="font-weight-bold">{item.label}</Form.Label>
                    <Form.Control type="text" value={item.value.value} readOnly/>
                  </Form.Group>
                )
              }
            }else {
              return (
                <Form.Group key={item.id}>
                  <Form.Label className="font-weight-bold">{item.label}</Form.Label>
                  <Form.Control type={item.type.name} placeholder={item.label} value={this.state.form[item.id] ? this.state.form[item.id] : ''} onChange={e => this.onInputChange(e, item.id, e.target.value)} required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]} />
                </Form.Group>
              )
            }  
          } else if (item.type.name === 'boolean') {
            if (item.properties['radio'] !== undefined) {
              return (
                <Form.Group key={item.id}>
                  <Form.Check type="radio" label={item.label} id={item.id} value={this.state.form[item.id] ? this.state.form[item.id] : false} name={item.properties['name']} onChange={e => this.onRadioButton(e, item.id, e.target.checked, item.properties['name'])} required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]} />
                  <Form.Control.Feedback type="invalid">
                    Must choose one.
                  </Form.Control.Feedback>
                </Form.Group>
              ); 
            } else {
              return (
                <Form.Group key={item.id}>
                  <Form.Check type="checkbox" label={item.label} value={this.state.form[item.id] ? this.state.form[item.id] : false} onChange={e => this.onInputChange(e, item.id, e.target.checked)} />
                </Form.Group>
              ); 
            }
          } else if (item.type.name === 'long') {
            return (
              <Form.Group key={item.id}>
                <Form.Label className="font-weight-bold">{item.label}</Form.Label>
                <Form.Control type="number" value={this.state.form[item.id] ? this.state.form[item.id] : item.properties['min']} min={item.properties['min']} onChange={e => this.onInputChange(e, item.id, e.target.value)}  required={item.validationConstraints.filter(item => item.name && item.name === 'required' ? true : false)[0]}/>
              </Form.Group>
            );
          }
        })}
      </div>
    );
  }
}

export default Forms;