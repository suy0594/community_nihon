import React from 'react';
import { useState, useEffect } from 'react';
import axios from 'axios';
import './PostsContainer.css';
import Post from '../PostContents/Post';

const PostsContainer = ({userId}) => {
    const [boards, setBoards] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/boards')
      .then(response => {
        setBoards(response.data); 
        console.log(userId);
      })
      .catch(error => {
        console.error('Error:', error); 
      });
  }, []); 
  return (
    
      <div className="tweet-list">
        <div className="tweet">
        {boards.length > 0 ? (
        boards.map(boards => (
          <Post key={boards.id} userId={userId} posterId={boards.userId} title={boards.title} text={boards.content} time={boards.created_time} />
        ))
      ) : (
        <p>Loading...</p> 
      )}
        </div>
      </div>
  );
};

export default PostsContainer;
