import { TestBed } from '@angular/core/testing';

import { FeedsService } from './feeds.service';

describe('PhotosService', () => {
  let service: FeedsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FeedsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
