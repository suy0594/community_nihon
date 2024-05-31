import React,{ useState, useEffect } from "react";
import { useNavigate, useParams } from 'react-router-dom';
import Post from "../PostContents/Post";
import axios from 'axios';
import './Group.css';


const Group = ({userId}) => {
    const navigate = useNavigate();
    const {groupId } = useParams(); // パラメータを直接取得
    const [group, setGroup] = useState([]);
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [imgError, setImgError] = useState(false);

    useEffect(() => {
        console.log("in Group groupId get from url" + groupId);
        console.log("in Group userId get from url " + userId);
        const fetchGroupData = async () => {
            try {
                const groupResponse = await axios.get(`http://localhost:8080/api/communities/${groupId}`);
                setGroup(groupResponse.data);
                const postsResponse = await axios.get(`http://localhost:8080/api/communities/${groupId}/posts`); //check api
                setPosts(postsResponse.data);
            } catch (error) {
                setError('Error fetching group data or posts');
                console.error('Error:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchGroupData();
    }, [groupId]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    const handleImgError = () => {
        setImgError(true);
      };
    return (
        <>
            <div className="profile-container">
                <div className="profile-header">
                <img
            src={imgError ? "/images/errorImage.jpg" : "/images/testaccountinfo/knu_emeblem.jpg"}
            className='pict'
            alt='account picture'
            onError={handleImgError}
          />
                    <div className="profile-info">
                        <h2 className="profile-name">{group.title}</h2>
                        <p className="profile-handle">@{group.id}</p>
                        <p className="profile-bio">{group.description}</p>
                        <div className="profile-stats">
                            <p>Posts: {group.number_of_posts}</p>
                            <p>Member: {group.number_of_member}</p>
                        </div>
                    </div>
                </div>
                <button onClick={() => {navigate(`./createGroupPost`);}}>CREATE IN GROUP POST
                </button>
                <div className="tweet-list">
                    {posts.length > 0 ? (
                        posts.map(post => (
                            <Post
                                key={post.id}
                                userId={userId}
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
        </>
    );
};

export default Group;