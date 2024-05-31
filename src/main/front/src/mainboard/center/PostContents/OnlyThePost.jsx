import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const OnlyThePost = ({userId}) => {
    const navigate = useNavigate();
    const [board ,setBoard] = useState();
    const { postId } = useParams();

    useEffect(() => {
        axios.get(`http://localhost:8080/api/boards/${userId}/${postId}`)
            .then(response => {
                setBoard(response.data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }, []);

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
        navigate(`/${ userId }/profile/${ board.posterId }`); 
    };


    if (!board) {
        return <div>Loading...</div>; 
    }
    return (
        <>
            <div className="tweet">
                <div className="tweet-header"  onClick={handleToProfile}>
                    <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture'></img>
                    <div className="user-info">
                        <span className="username">@{board.posterId}</span>
                        <span className="handle" >{board.title}</span>
                    </div>
                </div>
                <hr></hr>
                <div className="tweet-content">
                    <p>
                        {board.content}
                    </p>
                </div>
                <div className="tweet-footer">
                    <span className="timestamp">{board.created_time}</span>
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

export default OnlyThePost;