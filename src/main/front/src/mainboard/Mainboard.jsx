import React from 'react';
import Leftsidemenu from './leftside/Leftsidemenu';
import Post from './center/Post';
import Notification from './rightside/Notification';
import './Mainboard.css';

const Mainboard = ({ userId, onLogout }) => {
  return (
    <div style={{display : 'flex'}}>
      <div className="leftside">
        <Leftsidemenu userId={userId} />
      </div>

      <div className='centerside'>
        <Post />
      </div>

      <div className='rightside'>
        <Notification />
      </div>
    </div>
  );
};

export default Mainboard;