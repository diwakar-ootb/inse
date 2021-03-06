const URL_PREFIX = 'http://localhost:4200/webapp';
const SECURED_URL_PREFIX = 'http://localhost:4200/webapp/rest';

export const environment = {
  production: false,
  AUTH_URL: URL_PREFIX + '/authenticate',

  PHOTOS_LIST_URL: SECURED_URL_PREFIX + '/photos/list',
  PHOTOS_PROGRESS_URL: SECURED_URL_PREFIX + '/photos/progress',
  POSTS_URL: SECURED_URL_PREFIX + '/posts/load',
  POSTS_STREAM_URL: SECURED_URL_PREFIX + '/posts/stream',
  POSTS_SAVE_URL: SECURED_URL_PREFIX + '/posts',
  NOTIFICATIONS_STREAM_URL: SECURED_URL_PREFIX + '/notifications/stream',
  MESSAGES_STREAM_URL: SECURED_URL_PREFIX + '/messages/stream',
  MESSAGE_GET_URL: SECURED_URL_PREFIX + '/messages/',
  NETWORK_STREAM_URL: SECURED_URL_PREFIX + '/network/stream',
  NETWORK_EXPLORE_URL: SECURED_URL_PREFIX + '/network/explore',
};
