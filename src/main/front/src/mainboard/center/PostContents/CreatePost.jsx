import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './CreatePost.css';

const CreatePost = ({userId, title = userId}) => {
  const [content, setContent] = useState('');
  const [boardId, setBoardId] = useState(null);
  const [error, setError] = useState(null);
  const [groupId, setGroupId] = useState('');

  const handleChange = (e) => {
    console.log(e.target.value);
      setContent(e.target.value);
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(content+" from submit"); console.log(userId+" from submit");console.log(title+" from submit");
    try {
      const data = {
        userId : userId,
        content: content,
        title: title
      }
      const response = await axios.post('http://localhost:8080/api/boards/create', data);
      setBoardId(response.data);
      setError(null);
      console.log('生成された掲示板のID:', response.data);
    } catch (error) {
      setError('ボードの作成に失敗しました');
      console.error('エラー:', error);
    }
  };

  return (
    <div className='CreatePost'>
      <h2 className='Title'>掲示板作成</h2>
      {error && <div style={{ color: 'red' }}>{error}</div>}
      <form onSubmit={handleSubmit} className='createPostForm'>
        <div>
          <textarea
            name="content"
            value={content}
            onChange={handleChange}
            className='CPTextarea'
          />
        </div>
        <button type="submit" className='CPButton'>作成</button>
      </form>
    </div>
  );
};

export default CreatePost;