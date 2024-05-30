import React, { useState } from 'react';
import LoginForm from './LoginForm';
import MakeAccount from './makeAccount/MakeAccount';

const Login = ({ onLogin }) => {
  const loginContainer = {
    position: 'absolute',        /* 位置指定 */
    padding: '100px',               /* 余白指定 */
    top:  '0',                   /* 位置指定 */
    bottom:  '0',                  /* 位置指定 */
    left:  '0',                /* 位置指定 */
    right:  '0',                  /* 位置指定 */
    margin:  'auto',               /* 中央寄せ */
    width:  'auto',               /* 幅指定 */
    /* 高さ指定 */
  };
  const [isShowMakeAcc, setShowMakeAcc] = useState(false);
  const handleShowMakeAcc = () => {
    setShowMakeAcc(!isShowMakeAcc);
  };
  return (
      <>
        <div style={loginContainer}>
          {isShowMakeAcc ? <MakeAccount /> :
              <LoginForm onLogin={onLogin} /> }
        </div>
      </>
  );
};

export default Login;