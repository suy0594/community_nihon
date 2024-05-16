import React, { useState } from 'react';
import Login from './login/Login';
import Mainboard from './mainboard/Mainboard'; // ログイン成功後のMainboardコンポーネント

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
    <div>
      {isLoggedIn ? ( // ログインしているかどうかで表示するコンポーネントを切り替える
        <Mainboard userId={userId} onLogout={handleLogout} /> // Mainboardを表示
      ) : (
        <Login onLogin={handleLogin} /> // ログイン画面を表示
      )}
    </div>
  );
};

export default App;
