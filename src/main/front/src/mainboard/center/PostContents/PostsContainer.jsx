import React from 'react';
import { useState, useEffect } from 'react';
import axios from 'axios';
import './PostsContainer.css';
import Post from '../PostContents/Post';

const PostsContainer = ({userID}) => {
  const [boards, setBoards] = useState([]); 

  useEffect(() => {
    axios.get('/api/boards') 
      .then(response => {
        setBoards(response.data); 
      })
      .catch(error => {
        console.error('Error:', error); 
      });
  }, []); 
  return (
    <div className="profile-container">
      
      <div className="tweet-list">
        <div className="tweet">
        {boards.length > 0 ? (
        boards.map(board => (
          <Post key={board.id} id={board.id} title={board.title} text={board.content} />
        ))
      ) : (
        <p>Loading...</p> 
      )}
        </div>
      </div>
    </div>
  );
};

export default PostsContainer;