import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './post.css';

const Post = ({postId, userId, posterId, title, text, time, onDelete}) => {
    const navigate = useNavigate();
    const handleLikeButton = async() => {

    };
    const handleReplyButton = () => {

    };
    const handleBookmarkButton = async() => {

    };
    const handleModifyButton = () => {

    };
    const handleDeleteButton = async (event) => {
        event.stopPropagation();
        try {
            console.log(`Attempting to delete post with ID: ${postId}`);
            await axios.delete(`http://localhost:8080/api/boards/${postId}`);
            console.log('Post deleted successfully');
            onDelete(postId); // 게시글이 삭제되었음을 상위 컴포넌트에 알림
            navigate(`/${userId}/home`);
        } catch (error) {
            console.error("Error deleting post", error);
        }
    };
    const handleToProfile = () => {
        navigate(`/${ userId }/profile/${ posterId }`);
    };
    const handleToOnlyThePost = () => {
        navigate(`/${ userId }/${ postId }`);
    };
    
    return(
        <>
            <div className="tweet">
                <div className="tweet-header"  onClick={handleToProfile}>
                    <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture'></img>
                    <div className="user-info">
                        <span className="username">@{posterId}</span>
                        <span className="handle" >{title}</span>
                    </div>
                </div>
                <hr></hr>
                <div className="tweet-content" onClick={handleToOnlyThePost}>
                    <p>
                        {text}
                    </p>
                </div>
                <div className="tweet-footer" onClick={handleToOnlyThePost}>
                    <span className="timestamp">{time}</span>
                    <div className="actions">
                        <button className="like-button" onClick={handleLikeButton}>Like<label></label></button>
                        <button className="reply-button" onClick={handleReplyButton}>Reply</button>
                        <button className="BookMark-button" onClick={handleBookmarkButton}>BookMark</button>
                        {userId === posterId && (
                            <>
                                <button className="Modify-button" onClick={handleModifyButton}>Modify</button>
                                <button className="Delete-button" onClick={handleDeleteButton}>Delete</button>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </>
    );
};

export default Post;