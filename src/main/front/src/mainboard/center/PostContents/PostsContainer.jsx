import React from 'react';
import { useState, useEffect } from 'react';
import axios from 'axios';
import './PostsContainer.css';
import Post from '../PostContents/Post';

const PostsContainer = ({userID}) => {
    const [boards, setBoards] = useState([]);

  useEffect(() => {
<<<<<<< HEAD
    axios.get('http://localhost:8080/api/boards')
=======
    axios.get('http://localhost:8080/api/boards') 
>>>>>>> a444d1e7cec8938358c33ba238f7d8cc45ee970c
      .then(response => {
        setBoards(response.data); 
      })
      .catch(error => {
        console.error('Error:', error); 
      });
  }, []); 
  return (
    
      <div className="tweet-list">
        <div className="tweet">
        {boards.length > 0 ? (
        boards.map(board => (
          <Post key={board.id} id={board.userId} title={board.title} text={board.content} time={board.created_time} />
        ))
      ) : (
        <p>Loading...</p> 
      )}
        </div>
      </div>
  );
};

export default PostsContainer;