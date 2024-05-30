import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const MakeAccount = () => {

  const divstyle = {
    width: '300px',
    margin: '0 auto',
    border: '2px solid #27acd9',
    borderRadius: '10px',
    padding: '15px',
    textAlign: 'center',
    boxShadow: '5px 5px #27acd9',
  };
  const inputContainer = {
    margin : '0.5rem'
  };
  const btnContainer = {
    display : 'flex'
  };
  const buttonstyle = {
    display: 'block',
    textAlign: 'center',
    verticalAlign: 'middle',
    textDecoration: 'none',
    position: 'relative',

    margin: 'auto',
    padding: '0.5rem 0.5rem',
    fontWeight: 'bold',
    borderRadius: '10px',
    color: '#27acd9',
    border: '1px solid #27acd9',
    boxShadow: '3px 1px #27acd9',
    transition: '0.3s ease-in-out',
  };
  const hoverOnstyle = {
    boxShadow: 'none',
    transform: 'translate(5px, 5px)',
    color: '#27acd9',
  };



  const [formData, setFormData] = useState({
    id: '',
    password: '',
    email: '',
    phone: '',
    screen_name: ''
  });
  const navigate = useNavigate();
  const [btnStyle, setStyle] = useState(buttonstyle);
  const onBtnhover = () => {
    setStyle({buttonstyle, hoverOnstyle});
  };
  const offBtnhover = () => {
    setStyle(buttonstyle);
  };
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/join', formData);
      console.log('Success:', response.data);
      navigate('/');
    } catch (error) {
      console.error('Error:', error);
    }

  };

  return (
      <div style={divstyle}>
        <h2>Create Account</h2>
        <hr></hr>
        <form onSubmit={handleSubmit}>
          <label>
            ID:
            <input type="text" name="id" value={formData.id} onChange={handleChange} />
          </label>
          <br />
          <label>
            Password:
            <input type="password" name="password" value={formData.password} onChange={handleChange} />
          </label>
          <br />
          <label>
            Email:
            <input type="email" name="email" value={formData.email} onChange={handleChange} />
          </label>
          <br />
          <label>
            Phone:
            <input type="text" name="phone" value={formData.phone} onChange={handleChange} />
          </label>
          <br />
          <label>
            Screen Name:
            <input type="text" name="screenName" value={formData.screenName} onChange={handleChange} />
          </label>
          <br />
          <button type="submit" style={buttonstyle} onMouseEnter={onBtnhover} onMouseLeave={offBtnhover}>Register</button>
        </form>
      </div>
  );
};

export default MakeAccount;
