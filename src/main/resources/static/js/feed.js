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

    // Display posts with followed tags first
    followedTags.forEach(tag => {
        const postsWithTag = postData.filter(post => post.text.includes(tag));
        postsWithTag.forEach(post => {
            const postElement = document.createElement('div');
            postElement.textContent = `${post.username}: ${post.text}`;
            feedElement.appendChild(postElement);
        });
    });

    // Display posts without followed tags
    const otherPosts = postData.filter(post => !followedTags.some(tag => post.text.includes(tag)));
    otherPosts.forEach(post => {
        const postElement = document.createElement('div');
        postElement.textContent = `${post.username}: ${post.text}`;
        feedElement.appendChild(postElement);
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
    window.location.href = `explore.html?search=${encodeURIComponent(searchTerm)}`;
}

// Call renderPosts function to initially render posts
renderPosts();

// Call renderRecommendations function to initially render recommendation tags
renderRecommendations();
