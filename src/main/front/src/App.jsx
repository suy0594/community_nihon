import React, { useState } from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate
} from 'react-router-dom';
import LoginForm from './login/LoginForm';
import MakeAccount from './login/makeAccount/MakeAccount';
import Login from './login/Login';
import Mainboard from './mainboard/Mainboard';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false); // Must change it to false later;
  const [userId, setUserId] = useState('');

  const handleLogin = (id) => {
    setIsLoggedIn(true);
    setUserId(id);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setUserId('');
  };

  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={isLoggedIn ? <Navigate to="/main/home" /> : <Login onLogin={handleLogin} />}
        />
        <Route path="/makeAccount" element={<MakeAccount />} />
        {isLoggedIn && (
          <Route
            path="/main/*"
            element={<Mainboard userId={userId} onLogout={handleLogout} />}
          />
        )}
        {!isLoggedIn && <Route path="/logout" element={<Navigate to="/" />} />}
      </Routes>
    </Router>
  );
};

export default App;