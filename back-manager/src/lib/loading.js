import { Loading } from 'element-ui';

let loading = null;
let loadingRequestCount = 0; //同一时刻请求数量

export function startLoading() {
  loading = Loading.service({
    lock: true,
    text: '加载中...',
    background: 'rgba(0, 0, 0, .7)',
  });
}

export function endLoading() {
  loading.close();
}

//同一时刻的请求合并
export function showFullScreenLoading() {
  if (loadingRequestCount === 0) {
    startLoading();
  }
  loadingRequestCount += 1;
}

export function hideFullScreenLoading() {
  if (loadingRequestCount <= 0) return;
  loadingRequestCount -= 1;
  if (loadingRequestCount === 0) {
    endLoading();
  }
}
