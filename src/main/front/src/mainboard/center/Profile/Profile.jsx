import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import './Profile.css';
import Post from '../PostContents/Post';
import axios from 'axios';

const Profile = (userId) => {
    const [profileData, setProfileData] = useState(null);
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isFollowing, setIsFollowing] = useState(false);
    const { posterId } = useParams();
    const [imgError, setImgError] = useState(false);

const handleImgError = () => {
    setImgError(true);
  };


    useEffect(() => {
        const fetchProfileData = async () => {
            try {
                const profileResponse = await axios.get(`http://localhost:8080/api/user/` + `${posterId}`);
                setProfileData(profileResponse.data);
                const postsResponse = await axios.get(`http://localhost:8080/api/user/` + `${userId.userId}` + `/profile/` + `${posterId}`);
                setPosts(postsResponse.data);
                const isFollowResponse = await axios.get(`http://localhost:8080/api/follows/` + `${userId.userId}` + `/profile/` + `${posterId}`);
                setIsFollowing(isFollowResponse.data);
            } catch (error) {
                setError('Error fetching profile data or posts');
                console.error('Error fetching profile data or posts:', error);
            } finally {
                console.log("posterID: " + posterId); console.log("userId : " + userId.userId);
                setLoading(false);
            }
        };

        fetchProfileData();
    }, [posterId, userId.userId]);
    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    const handleFollowClick = async () => {
        try {
            if (isFollowing) {
                await axios.delete(`http://localhost:8080/api/follows/${posterId}/follow/` + `${userId.userId}`);
            } else {
                await axios.post(`http://localhost:8080/api/follows/${posterId}/follow/` +  `${userId.userId}`);
            }
            setIsFollowing(!isFollowing);
        } catch (error) {
            console.error('Failed to follow/unfollow user:', error);
        }
    };

    return (
        <div className="profile-container">
            <div className="profile-header">
            <img
            src={imgError ? "/images/errorImage.jpg" : "/images/testaccountinfo/knu_emeblem.jpg"}
            className='pict'
            alt='account picture'
            onError={handleImgError}
          />
                <div className="profile-info">
                    <h2 className="profile-name">{profileData.screen_name}</h2>
                    <p className="profile-handle">@{posterId}</p>
                    <p className="profile-bio">{profileData.introduction}</p>
                    <div className="profile-stats">
                        <p>Posts: {profileData.number_of_posts}</p>
                        <p>Following: {profileData.following_count}</p>
                        <p>Followers: {profileData.follower_count}</p>
                    </div>
                </div>
                <button onClick={handleFollowClick}>
                    {isFollowing ? 'アンフォロー' : 'フォロー'}
                </button>
            </div>
            <div className="tweet-list">
                {posts.length > 0 ? (
                    posts.map(post => (
                        <Post
                            key={post.id}
                            postId={post.id}
                            userId={userId.userId}
                            posterId={post.userId}
                            title={post.title}
                            text={post.content}
                            time={post.created_time}
                        />
                    ))
                ) : (
                    <p>No posts available</p>
                )}
            </div>
        </div>
    );
};

export default Profile;
