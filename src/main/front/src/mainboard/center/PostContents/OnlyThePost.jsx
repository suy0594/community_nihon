import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import ReplyInput from './ReplyInput';

const OnlyThePost = ({ userId }) => {
    const navigate = useNavigate();
    const [board, setBoard] = useState();
    const [showReplyInput, setShowReplyInput] = useState(false);
    const [error, setError] = useState(null);
    const { postId } = useParams();

    useEffect(() => {

        axios.get(`http://localhost:8080/api/boards/${userId}/${postId}`)
            .then(response => {
                setBoard(response.data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }, [postId]);

    const handleLikeButton = async () => {
        // Like button functionality
    };

    const handleReplyButton = () => {
        setShowReplyInput(!showReplyInput);
    };

    const handleBookmarkButton = async () => {
        // Bookmark button functionality
    };

    const handleModifyButton = () => {
        // Modify button functionality
    };

    const handleDeleteButton = () => {
        // Delete button functionality
    };

    const handleToProfile = () => {
        navigate(`/${userId}/profile/${board.posterId}`);
    };

    const handleReplySubmit = async(content) => {
        console.log('Reply submitted:', content);
        try {
            const data = {
                userId : userId,
                postId : postId,
                content: content
              }
            const response = await axios.post('http://localhost:8080/api/replies/create', data);
            setBoard(response.data);
            setError(null);
            console.log('生成されたreplyのID:', response.data);
          } catch (error) {
            setError('ボードの作成に失敗しました');
            console.error('エラー:', error);
          }
    };

    if (!board) {
        return <div>Loading...</div>;
    }

    return (
        <>
            <div className="tweet">
                <div className="tweet-header" onClick={handleToProfile}>
                    <img src="/testaccountinfo/knu_emeblem.jpg" className='pict' alt='account picture'></img>
                    <div className="user-info">
                        <span className="username">@{board.posterId}</span>
                        <span className="handle">{board.title}</span>
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
            {showReplyInput && <ReplyInput onSubmit={handleReplySubmit} />}
        </>
    );
};

export default OnlyThePost;
