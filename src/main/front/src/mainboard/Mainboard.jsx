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
import RightSideMenu from './rightside/RightSideMenu';
import CreateInGroupPost from './center/Group/CreateInGroupPost';
import Profile from './center/Profile/Profile';
import OnlyThePost from './center/PostContents/OnlyThePost';
import './Mainboard.css';
import Group from "./center/Group/Group";

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
                        <Route path="groups" element={<GroupsContainer />} />
                        <Route path="search" element={<Search />} />
                        <Route path="setting" element={<Setting Logout={onLogout} />} />
                        <Route path='createPost' element={<CreatePost userId={userId} />} />
                        <Route path='createGroup' element={<CreateGroup userId={userId} />} />
                        <Route path='groups/:id/createGroupPost' element={<CreateInGroupPost userId={userId} />} />
                        <Route path="profile/:posterId" element={<Profile userId={userId} />} />
                        <Route path="groups/:groupId" element={<Group userId={userId}/>} />
                        <Route path=':postId' element={<OnlyThePost userId={userId}/>} />
                    </Routes>
                </div>
                <div className='rightside'>
                    <RightSideMenu userId={userId} />
                </div>
            </div>
        </>
    );
};

export default Mainboard;
