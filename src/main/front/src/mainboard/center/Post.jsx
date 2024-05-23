import React from 'react';
import PostHead from './PostContents/PostHead';
import PostBody from './PostContents/PostBody';
import PostFoot from './PostContents/PostFoot';

const Post = () => {
    return(
        <>
        <PostHead />
        <PostBody />
        <PostFoot />
        </>
    );
};

export default Post;