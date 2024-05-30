import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './PostsContainer.css';
import Post from '../PostContents/Post';

const PostsContainer = ({ userId }) => {
    const [boards, setBoards] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/boards/${userId}`)
            .then(response => {
                setBoards(response.data);
                console.log("유저 아이디: " + userId);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }, [userId]);

    return (
        <div className="tweet-list">
            <div className="tweet">
                {boards.length > 0 ? (
                    boards.map(board => (
                        <Post key={board.id} userId={userId} posterId={board.userId} title={board.title} text={board.content} time={board.created_time} like={board.like}/>
                    ))
                ) : (
                    <p>Loading...</p>
                )}
            </div>
        </div>
    );
};

export default PostsContainer;
