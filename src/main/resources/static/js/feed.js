// Sample data of posts
let postData = [
    { username: "user1", text: "This is the first post. #hello" },
    { username: "user2", text: "Hello, world! #greeting" },
    { username: "user3", text: "Just sharing some thoughts. #thoughts" },
    // Add more posts here
];

// Sample recommendation tags
const recommendationTags = ['#love', '#happy', '#travel', '#food', '#music', '#art'];

// Array to store followed tags (mock data)
let followedTags = ['#love', '#food', '#greeting']; // Mock data of followed tags

// Function to render recommendation tags
function renderRecommendations() {
    const recommendationsElement = document.getElementById('recommendations');
    recommendationsElement.innerHTML = '';

    recommendationTags.forEach(tag => {
        const tagElement = document.createElement('span');
        tagElement.textContent = tag;
        tagElement.classList.add('recommendation');

        // Add click event listener to each recommendation tag
        tagElement.addEventListener('click', () => redirectToExplore(tag));

        // Add follow button
        const followButton = document.createElement('button');
        followButton.textContent = "Follow";
        followButton.addEventListener('click', () => followTag(tag));

        tagElement.appendChild(followButton);
        recommendationsElement.appendChild(tagElement);
    });
}

// Function to follow a tag
function followTag(tag) {
    if (!followedTags.includes(tag)) {
        followedTags.push(tag);
    }
    renderPosts();
}

// Function to render posts
function renderPosts() {
    const feedElement = document.getElementById('feed');
    feedElement.innerHTML = '';

    postData.forEach(post => {
        const postElement = document.createElement('div');
        postElement.classList.add('post');

        const postContent = document.createElement('span');
        postContent.innerHTML = `<b>${post.username}</b>: ${post.text}`;

        const commentButton = document.createElement('button');
        commentButton.textContent = "Comment";
        commentButton.addEventListener('click', () => commentOnPost(post.username, post.text));

        postElement.appendChild(postContent);
        postElement.appendChild(commentButton);

        const postLink = document.createElement('a');
        postLink.href = "#";
        postLink.onclick = () => redirectToPostPage(post.username, post.text);
        postLink.appendChild(postElement);

        feedElement.appendChild(postLink);
    });
}

// Function to submit a new post
function submitPost() {
    const textInput = document.getElementById('textInput');
    const username = document.querySelector('span[sec\\:authentication="name"]').textContent;
    let text = textInput.value.trim();

    // Check if both username and text are provided
    if (username && text) {
        // Check if the text contains hashtags
        const hashtags = text.match(/#\w+/g);
        if (hashtags) {
            // Add hashtags to the post
            hashtags.forEach(tag => {
                text += ` ${tag}`;
            });
        }

        // Add the new post to the beginning of the postData array
        postData.unshift({ username, text });

        // Clear input field
        textInput.value = '';

        // Render posts with the updated data
        renderPosts();
    } else {
        alert('Please provide post text.');
    }
}

// Function to handle comment button clicks
function commentOnPost(username, text) {
    // Implement your comment functionality here
    console.log(`Comment on post by ${username}: ${text}`);
}

// Function to handle post clicks and redirect to the dedicated post page
function redirectToPostPage(username, text) {
    // You can implement the redirection logic based on your application's routing
    console.log(`Redirecting to post page for post by ${username}: ${text}`);
    // Example: window.location.href = '/post/' + postId;
}

// Function to filter posts based on hashtags
function searchPosts() {
    const searchInput = document.getElementById('searchInput');
    const searchTerm = searchInput.value.toLowerCase();

    if (searchTerm) {
        redirectToExplore(searchTerm);
    }
}

// Function to redirect to explore.html with search term parameter
function redirectToExplore(searchTerm) {
    window.location.href = '/explore?search=' + encodeURIComponent(searchTerm);
}

// Add event listener to the search button
document.getElementById('searchButton').addEventListener('click', searchPosts);

// Call renderPosts function to initially render posts
renderPosts();

// Call renderRecommendations function to initially render recommendation tags
renderRecommendations();
