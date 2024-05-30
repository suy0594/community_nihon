import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Leftsidemenu from './leftside/Leftsidemenu';

import Posts from './center/PostContents/PostsContainer';
import Notification from './rightside/Notification';
import BoardButton from './rightside/BoardButton';
import CreateGroupButton from './rightside/CreateGroupButton';
import MyAccount from './center/MyAccount/MyAccount';
import GroupsContainer from './center/Group/GroupsContainer';
import Search from './center/Search/Search';
import Setting from './center/Setting/Setting';
import CreatePost from './center/PostContents/CreatePost';
import CreateGroup from './center/Group/CreateGroup';
import RightSideMemu from './rightside/RightSideMenu';
import CreateInGroupPost from './center/Group/CreateInGroupPost';
import Profile from './center/Profile/Profile';
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
          <Route path="home" element={<Posts userId={userId} />} />
          <Route path="my" element={<MyAccount userId={userId}/>} />
          <Route path="groups" element={<GroupsContainer /> } />
          <Route path="search" element={<Search />} />
          <Route path="setting" element={<Setting Logout={onLogout} />} />
          <Route path='createPost' element={<CreatePost userId={userId} title={userId} /> } />
          <Route path='createGroup' element={<CreateGroup userId={userId}/> } />
          <Route path='groups/${id}/createGroupPost' element={<CreateInGroupPost userId={userId} /> } />  
          <Route path="/profile" element={<Profile />} />
        </Routes>
      </div>

      <div className='rightside'>
        <RightSideMemu userId={userId}/>
      </div>
    </div>
    
    </>
  );
};

export default Mainboard;
