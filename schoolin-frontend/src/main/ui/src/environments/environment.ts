const URL_PREFIX = 'http://localhost:4200/webapp';
const SECURED_URL_PREFIX = 'http://localhost:4200/webapp/rest';

export const environment = {
  production: false,
  AUTH_URL: URL_PREFIX + '/authenticate',
  
  FEEDS_URL: SECURED_URL_PREFIX + '/feeds/load',
  PHOTOS_LIST_URL: SECURED_URL_PREFIX + '/photos/list',
  PHOTOS_STREAM_URL: SECURED_URL_PREFIX + '/feeds/stream',
  PHOTOS_PROGRESS_URL: SECURED_URL_PREFIX + '/photos/progress'
};
