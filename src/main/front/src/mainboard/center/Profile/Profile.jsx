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
    const { posterId } = useParams();

    const [isFollowing, setIsFollowing] = useState(false);

    useEffect(() => {
        const fetchProfileData = async () => {
            try {
                console.log("유저~~ : " + userId.userId);
                console.log("포스터~~ : " + posterId);
                const profileResponse = await axios.get(`http://localhost:8080/api/user/` + posterId);
                setProfileData(profileResponse.data);
                const postsResponse = await axios.get(`http://localhost:8080/api/user/` + userId.userId + `/profile/` + posterId);
                console.log("게시판게시판: " + postsResponse.data);
                setPosts(postsResponse.data);
            } catch (error) {
                setError('Error fetching profile data or posts');
                console.error('Error fetching profile data or posts:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchProfileData();
    }, [posterId]);
    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    const handleFollowClick = async () => {
        try {
          if (isFollowing) {
            await axios.delete(`http://localhost:8080/api/follows/${userId}/follow`);
          } else {
            await axios.post(`http://localhost:8080/api/follows/${userId}/follow`);
          }
          setIsFollowing(!isFollowing);
        } catch (error) {
          console.error('Failed to follow/unfollow user:', error);
        }
      };

    return (
        <div className="profile-container">
            <div className="profile-header">
                <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture' />
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
                            userId={posterId}
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
