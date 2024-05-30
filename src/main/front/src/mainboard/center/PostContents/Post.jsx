import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './post.css';

const Post = ({posterId, title, text, time}) => {
    const navigate = useNavigate();
    const handleLikeButton = async() => {
        
    };
    const handleReplyButton = () => {

    };
    const handleBookmarkButton = async() => {
       
    };
    const handleModifyButton = () => {

    };
    const handleDeleteButton = () => {

    };
    const handleToProfile = () => {
        console.log("clicked");
            navigate(`/profile?userId=${posterId}`); 
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