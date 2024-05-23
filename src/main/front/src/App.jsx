import React, { useState } from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from 'react-router-dom';
import LoginForm from './login/LoginForm';
import MakeAccount from './login/makeAccount/MakeAccount';
import Login from './login/Login';
import Mainboard from './mainboard/Mainboard';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false); // ログイン状態を管理するState => false
  const [userId, setUserId] = useState(''); // ログインしたユーザーのIDを管理するState => ID = ' '

  const handleLogin = (id) => {
    setIsLoggedIn(true); // ログイン状態をtrueに設定
    setUserId(id); // ログインしたユーザーのIDを設定
  };

  const handleLogout = () => {
    setIsLoggedIn(false); // ログアウト状態をtrueに設定
    setUserId(''); // ユーザーIDをクリア
  };

  return (
    <Router>
      {isLoggedIn ? ( // ログインしているかどうかで表示するコンポーネントを切り替える
        <Mainboard userId={userId} onLogout={handleLogout} /> // Mainboardを表示
      ) : (
        <>
          <Routes>
            <Route path="/" element={<Login onLogin={handleLogin} />} />
            <Route path="/make-account" element={<MakeAccount />} />
          </Routes>
        </>
      )}
    </Router>
  );
};

export default App;
*/
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Join from './Login'; // Join 컴포넌트를 import 합니다.

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/user/login" element={<Join />} />
                {/* 다른 라우트들을 이곳에 추가할 수 있습니다. */}
            </Routes>
        </Router>
    );
}

export default App;