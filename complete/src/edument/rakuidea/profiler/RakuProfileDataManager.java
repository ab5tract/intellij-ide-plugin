package edument.rakuidea.profiler;

import edument.rakuidea.profiler.model.RakuProfileData;

import java.util.Deque;

public interface RakuProfileDataManager {
    Deque<RakuProfileData> getProfileResults();
    void saveProfileResult(RakuProfileData data);
    void removeProfileResult(RakuProfileData data);
}
