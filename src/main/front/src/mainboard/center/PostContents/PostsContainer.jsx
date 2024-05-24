import React from 'react';
import './PostsContainer.css';
import Post from '../PostContents/Post';

const PostsContainer = () => {
  return (
    <div className="profile-container">
      
      <div className="tweet-list">
        <div className="tweet">
          <Post />
        </div>
        <div className="tweet">
          <Post />
        </div>
      </div>
    </div>
  );
};

export default PostsContainer;