async function getList({bno, page, size, goLast}){
    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})

    if(goLast){
        const total = result.data.total
        const lastPage = parseInt(Math.ceil(total/size))
        return getList({bno:bno, page:lastPage, size:size})
    }

    return result.data
}

//댓글 추가하기
async function addReply(replyOjb) {
    const result = await axios.post(`/replies/`, replyOjb);
    return result.data
}