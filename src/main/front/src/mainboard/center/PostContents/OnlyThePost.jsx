import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Post from '../PostContents/Post';
import { useParams } from 'react-router-dom';
import ReplyInput from './ReplyInput';

const OnlyThePost = ({ userId }) => {
    const navigate = useNavigate();
    const [board, setBoard] = useState();
    const [replies, setReplies] = useState([]);
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
            console.log(response.data);
            setError(null);
            console.log('生成されたreplyのID:', response.data);

            // 게시글 데이터 다시 불러오기
            const boardResponse = await axios.get(`http://localhost:8080/api/boards/${userId}/${postId}`);
            

            // 답글 목록 다시 불러오기
            const repliesResponse = await axios.get(`http://localhost:8080/api/replies/${postId}`);
            setReplies(repliesResponse.data);
        } catch (error) {
            setError('ボードの作成に失敗しました');
            console.error('エラー:', error);
        }
    };

    useEffect(() => {
        axios.get(`http://localhost:8080/api/replies/${postId}`)
            .then(response => {
                setReplies(response.data);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }, [userId, postId]);

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
            <div className="tweet">
                {replies.length > 0 ? (
                    replies.map(reply => (
                        <Post key={reply.id} postId={reply.id} userId={userId} posterId={reply.userId} title={reply.title} text={reply.content} time={reply.created_time} />

                    ))
                ) : (
                    <p>Loading...</p>
                )}
            </div>
        </>
    );
};

export default OnlyThePost;
