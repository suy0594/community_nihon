import React, { useState, useEffect } from 'react';

const MakeAccountForm = () => {

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

  const [users, setUsers] = useState([]);
  const [id, setId] = useState(''); // IDの状態を管理するState
  const [password, setPassword] = useState(''); // パスワードの状態を管理するState
  const [userName, setName] = useState('');
  const [error, setError] = useState(''); // エラーメッセージの状態を管理するState

  useEffect(() => {
    fetch('/users.json')
      .then(response => response.json())
      .then(data => setUsers(data.users))
      .catch(error => console.error('Error fetching users:', error));
  }, []);
  const makeAcc = (user) =>{
    setUsers([...users, user]);
  };

  const handleLogin = (e) => {
    e.preventDefault(); // フォームのデフォルトの動作をキャンセル
    // ログイン処理を行う
    if (id === 'user' && password === 'aa') {
      makeAcc(id); // ログインが成功した場合に、親コンポーネントにIDを渡す
    } else {
      setError('Invalid ID or password'); // ログインが失敗した場合にエラーメッセージを設定
    }
  };
  const [btnStyle, setStyle] = useState(buttonstyle);
  const onBtnhover = () => {
    setStyle({buttonstyle, hoverOnstyle});
  };
  const offBtnhover = () => {
    setStyle(buttonstyle);
  };

  return (
    <div style={divstyle}>
      <h2>Create Account</h2>
      <hr></hr>
      <form onSubmit={handleLogin}>

        <div style={inputContainer}>
          <div>
            <label>ID : </label>
            <input type="text" value={id} onChange={(e) => setId(e.target.value)} />
          </div>
          <div>
            <label>PW : </label>
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </div>
          <div>
            <label>NAME : </label>
            <input type="text" value={userName} onChange={(e) => setName(e.target.value)} />
          </div>
        </div>

        <div style={btnContainer}>
          <button style={buttonstyle} onMouseEnter={onBtnhover} onMouseLeave={offBtnhover} type="submit">Create</button>
          {error && <p style={{ color: 'red' }}>{error}</p>} {/* エラーメッセージがあれば表示 */}
        </div>
      </form>
    </div>
  );
};

export default MakeAccountForm;
