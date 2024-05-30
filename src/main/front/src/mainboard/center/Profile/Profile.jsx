import React, { useState, useEffect } from 'react';
import './Profile.css';
import Post from '../PostContents/Post';
import axios from 'axios';

const Profile = ({ userId }) => {
    const [profileData, setProfileData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProfileData = async () => {
            try {

                const response = await axios.post(`http://localhost:8080/api/user/${userId}`);

                setProfileData(response.data);
            } catch (error) {
                setError('Error fetching profile data');
                console.error('Error fetching profile data:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchProfileData();
    }, [userId]);

    const handleLikeButton = async () => {
        try {
            const response = await axios.post('/api/user', { userId });
            console.log('Bookmark created:', response.data);
        } catch (error) {
            console.error('Error creating bookmark:', error);
        }
    };

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div className="profile-container">
            <div className="profile-header">
                <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture' />
                <div className="profile-info">
                    <h2 className="profile-name">{profileData.screen_name}</h2>
                    <p className="profile-handle">@{userId}</p>
                    <p className="profile-bio">{profileData.introduction}</p>
                    <div className="profile-stats">
                        <p>Posts: {profileData.number_of_posts}</p>
                        <p>Following: {profileData.following_count}</p>
                        <p>Followers: {profileData.follower_count}</p>
                    </div>
                </div>
            </div>
            <div className="tweet-list">
                <div className="tweet">

                </div>
            </div>
        </div>
    );
};

export default Profile;
