import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Leftsidemenu from './leftside/Leftsidemenu';

import Posts from './center/PostContents/PostsContainer';
import Notification from './rightside/Notification';
import MyAccount from './center/MyAccount/MyAccount';
import Search from './center/Search/Search';
import Setting from './center/Setting/Setting';
import './Mainboard.css';

const Mainboard = ({ userId, onLogout }) => {
  return (
    <>
    <div style={{ display: 'flex' }}>
      <div className="leftside">
        <Leftsidemenu userId={userId} />
      </div>

      <div className='centerside'>
        <Routes>
          <Route path="home" element={<Posts />} />
          <Route path="my" element={<MyAccount />} />
          <Route path="search" element={<Search />} />
          <Route path="setting" element={<Setting Logout={onLogout} />} />
        </Routes>
      </div>

      <div className='rightside'>
        <Notification />
      </div>
    </div>
    
    </>
  );
};

export default Mainboard;
