import React, { useState, useEffect } from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from 'react-router-dom';
import LoginForm from './login/LoginForm';
import MakeAccount from './login/makeAccount/MakeAccount';
import Login from './login/Login';
import Mainboard from './mainboard/Mainboard';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userId, setUserId] = useState('');
  const [isLoggedOut, setIsLoggedOut] = useState(false);

  useEffect(() => {
    console.log("isLoggedIn:", isLoggedIn);
    console.log("userId:", userId);
    console.log("isLoggedOut:", isLoggedOut);
  }, [isLoggedIn, userId, isLoggedOut]);

  const handleLogin = (id) => {
    setIsLoggedIn(true);
    setUserId(id);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setUserId('');
    setIsLoggedOut(true);
  };

  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={
            isLoggedOut ? (
              <Login onLogin={handleLogin} /> // ログアウト状態ではLoginページを表示
            ) : isLoggedIn ? (
              <Navigate to={`/${userId}/home`} />
            ) : (
              <Login onLogin={handleLogin} />
            )
          }
        />
        <Route path="/makeAccount" element={<MakeAccount />} />
        {isLoggedIn && (
          <Route
            path={`/${userId}/*`}
            element={<Mainboard userId={userId} onLogout={handleLogout} />}
          />
        )}
      </Routes>
    </Router>
  );
};

export default App;