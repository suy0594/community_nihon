import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import '../PostContents/CreatePost.css';

const CreateInGroupPost = ({userId}) => {
  const [content, setContent] = useState('');
  const [boardId, setBoardId] = useState(null);
  const [error, setError] = useState(null);
  const { id } = useParams();

  /*
  useEffect(() => {
    // URLからパス部分を取得
    const path = window.location.pathname;
    // パスをスラッシュ('/')で分割し、最後の要素を取得
    const parts = path.split('/');
    const lastPart = parts[parts.length - 1];
    console.log("lastttttttttttttt  " + lastPart);
    // groupIdとして設定
    setTitle(lastPart);
  }, []);
  */
  const handleChange = (e) => {
    console.log("in handleChange target.value : " + e.target.value);
      setContent(e.target.value);
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(content+" from submit"); console.log(userId+" from submit");console.log(id+" from submit");
    try {
      const data = {
        id : id,
        userId : userId,
        content: content
      }
      console.log("-------sd-adasdasdasdasd:   " + userId);
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
      <h2 className='Title'>Group掲示板作成</h2>
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

export default CreateInGroupPost;