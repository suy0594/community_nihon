import React, { useState } from 'react';
import axios from 'axios';

const MakeAccount = () => {
  const [formData, setFormData] = useState({
    id: '',
    password: '',
    email: '',
    phone: '',
    screen_name: ''
  });

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
      const response = await axios.post('http://localhost:8080/user/join', formData);
      console.log('Success:', response.data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
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
          <input type="text" name="screen_name" value={formData.screen_name} onChange={handleChange} />
        </label>
        <br />
        <button type="submit">Register</button>
      </form>
  );
};

export default MakeAccount;