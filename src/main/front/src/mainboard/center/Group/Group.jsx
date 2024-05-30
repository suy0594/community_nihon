import React,{ useState, useEffect } from "react";
import { useNavigate, useParams } from 'react-router-dom';
import Post from "../PostContents/Post";
import axios from 'axios';

const Group = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [group, setGroup] = useState([]);
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchGroupData = async () => {
            try {
                const groupResponse = await axios.get(`http://localhost:8080/api/communities/${id}`);
                setGroup(groupResponse.data);
                const postsResponse = await axios.get(`http://localhost:8080/api/communities/${id}/posts`);
                setPosts(postsResponse.data);
            } catch (error) {
                setError('Error fetching group data or posts');
                console.error('Error:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchGroupData();
    }, [id]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>{error}</p>;

    return (
        <>
            <div className="profile-container">
                <div className="profile-header">
                    <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture'></img>
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
                                userId={id}
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