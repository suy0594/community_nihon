import React, { useState } from 'react';
import axios from 'axios';
import './CreatePost.css';

const CreatePost = ({userId, title}) => {
  const [content, setContent] = useState('');
  const [boardId, setBoardId] = useState(null);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
      setContent(value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('/api/boards', { title, content, userId });
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
