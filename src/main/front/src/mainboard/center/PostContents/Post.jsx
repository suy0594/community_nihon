import React from 'react';
import axios from 'axios';
import './post.css';

//need => id, title, text, + post time, userId

const Post = ({id, title, text, time}) => {
    const handleLikeButton = async() => {
        try {
            const response = await axios.post('/api/like_yous', {
                userId: id,
                content: text
            });
            console.log('Bookmark created:', response.data);
        } catch (error) {
            console.error('Error creating bookmark:', error);
        }
    };
    const handleReplyButton = () => {

    };
    const handleBookmarkButton = async() => {
        try {
            const response = await axios.post('/api/bookmarks', {
                userId: id,
                content: text
            });
            console.log('Bookmark created:', response.data);
        } catch (error) {
            console.error('Error creating bookmark:', error);
        }
    };
    const handleModifyButton = () => {

    };
    const handleDeleteButton = () => {

    };
    return(
        <>
        <div className="tweet">
            <div className="tweet-header">
                <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture'></img>
                <div className="user-info">
                    <span className="username">@{id}</span>
                    <span className="handle">{title}</span>
                </div>
            </div>
            <hr></hr>
            <div className="tweet-content">
                <p>
                    {text}
                </p>
            </div>
            <div className="tweet-footer">
                <span className="timestamp">{time}</span>
                <div className="actions">
                    <button className="like-button" onClick={handleLikeButton}>Like</button>
                    <button className="reply-button" onClick={handleReplyButton}>Reply</button>
                    <button className="BookMark-button" onClick={handleBookmarkButton}>BookMark</button>
                    <button className="Modify-button" onClick={handleModifyButton}>Modify</button>
                    <button className="Delete-button" onClick={handleDeleteButton}>Delete</button>
                </div>
            </div>
        </div>
        </>
    );
};

export default Post;