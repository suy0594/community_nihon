import React from 'react';
import './Profile.css';
import Post from '../PostContents/Post';

//need account info = username, userId, user introduction, number of posts, following, followers

const Profile = () => {
    return (
        <div className="profile-container">
            <div className="profile-header">
                <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture'></img>
                <div className="profile-info">
                    <h2 className="profile-name">user</h2>
                    <p className="profile-handle">@userID</p>
                    <p className="profile-bio">This is profile description space.</p>
                    <div className="profile-stats">
                        <p>Posts: 100</p>
                        <p>Following: 50</p>
                        <p>Followers: 200</p>
                    </div>
                </div>
            </div>
            <div className="tweet-list">
                <div className="tweet">
                    <Post />
                </div>
                <div className="tweet">
                    <Post />
                </div>
            </div>
        </div>
    );
};

export default Profile;
