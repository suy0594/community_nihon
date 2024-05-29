import React, { useState } from 'react';
import axios from 'axios';

const CreatePost = ({userId}) => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [boardId, setBoardId] = useState(null);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === 'title') {
      setTitle(value);
    } else if (name === 'content') {
      setContent(value);
    }
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


//comment to add github 



  return (
    <div>
      <h2>掲示板作成</h2>
      {error && <div style={{ color: 'red' }}>{error}</div>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>題名:</label>
          <input
            type="text"
            name="title"
            value={title}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>内容:</label>
          <textarea
            name="content"
            value={content}
            onChange={handleChange}
          />
        </div>
        <button type="submit">作成</button>
      </form>
    </div>
  );
};

export default CreatePost;
