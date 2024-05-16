import React from 'react';
import MakeAccountForm from './MakeAccountForm';

const MakeAccount = ({ makeAcc }) => {
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
  return (
    <div style={loginContainer}>
      <MakeAccountForm  /> 
    </div>
  );
};

export default MakeAccount;