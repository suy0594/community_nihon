import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Alert from '@mui/material/Alert';

const LoginForm = ({ onLogin }) => {
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
    margin: '0.5rem',
  };
  const btnContainer = {
    display: 'flex',
    flexDirection: 'column',
    gap: '10px',
    alignItems: 'center',
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

  const navigate = useNavigate();
  const [username, setId] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/login?', { username, password });
      if (response.data==="success") {
        <Alert severity="success">Success for Login.</Alert>
        onLogin(username);
        navigate('/');
      } else {
        setError('Invalid ID or password');
      }
    } catch (error) {
      console.error('Error:', error);
      setError('An error occurred during login');
    }
  };

  const [loginBtnStyle, setLoginBtnStyle] = useState(buttonstyle);
  const [createAccBtnStyle, setCreateAccBtnStyle] = useState(buttonstyle);

  const handleMakeAcc = () => {
    navigate('/makeAccount');
  };

  const onLoginBtnHover = () => {
    setLoginBtnStyle({ ...buttonstyle, ...hoverOnstyle });
  };

  const offLoginBtnHover = () => {
    setLoginBtnStyle(buttonstyle);
  };

  const onCreateAccBtnHover = () => {
    setCreateAccBtnStyle({ ...buttonstyle, ...hoverOnstyle });
  };

  const offCreateAccBtnHover = () => {
    setCreateAccBtnStyle(buttonstyle);
  };

  return (
    <div style={divstyle}>
      <h2>Login</h2>
      <hr></hr>
      <form onSubmit={handleLogin}>
        <div style={inputContainer}>
          <div>
            <label>ID : </label>
            <input type="text" value={username} onChange={(e) => setId(e.target.value)} />
          </div>
          <div>
            <label>PW : </label>
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </div>
        </div>

        <div style={btnContainer}>
          <button
            style={loginBtnStyle}
            onMouseEnter={onLoginBtnHover}
            onMouseLeave={offLoginBtnHover}
            type="submit"
          >
            Login
          </button>
          {error && <Alert severity="error">{error}</Alert>}
        </div>
      </form>

      <button
        style={createAccBtnStyle}
        onMouseEnter={onCreateAccBtnHover}
        onMouseLeave={offCreateAccBtnHover}
        onClick={handleMakeAcc}
      >
        Create Account
      </button>
    </div>
  );
};

export default LoginForm;
