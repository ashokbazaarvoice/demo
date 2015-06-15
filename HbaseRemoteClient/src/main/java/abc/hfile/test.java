package abc.hfile;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
//import com.merck.huron.processing.client.aggregation.AggregationResult;
//import com.merck.huron.processing.client.aggregation.ResultAggregator;
//import com.merck.mrlsearch.labnotebooks.SuggestedExperimentsCard;
//import com.merck.mrlsearch.labnotebooks.SuggestedExperimentsCard.SuggestedExperimentInfo;
//import com.merck.mrlsearch.labnotebooks.SuggestedExperimentsCard.SuggestedExperimentsInformation;

public class test
//        implements
//        ResultAggregator<SuggestedExperimentsCard>
{
//
//    private static Logger LOG = LoggerFactory
//            .getLogger(SuggestedELNNotebooksCardAggregator.class);
//
//    public static Function<SuggestedExperimentInfo, String> EXTRACT_TITLE = new Function<SuggestedExperimentInfo, String>() {
//        public String apply(SuggestedExperimentInfo input) {
//            return input.getDeptName();
//        }
//    };
//
//    public static Function<Collection<SuggestedExperimentInfo>, SuggestedExperimentInfo> MERGE_SUGGINFO = new Function<Collection<SuggestedExperimentInfo>, SuggestedExperimentInfo>() {
//        public SuggestedExperimentInfo apply(
//                Collection<SuggestedExperimentInfo> input) {
//            SuggestedExperimentInfo suggestedElnNotebooksInfo = null;
//            for (SuggestedExperimentInfo info : input) {
//                if (suggestedElnNotebooksInfo == null) {
//                    suggestedElnNotebooksInfo = info;
//                } else {
//                    suggestedElnNotebooksInfo.setDeptName(info.getDeptName());
//                    if (info.getDeptContacts() == null) {
//                        suggestedElnNotebooksInfo.setDeptContacts(new HashSet<String>());
//                    } else {
//                        suggestedElnNotebooksInfo.setDeptContacts(info.getDeptContacts());
//                    }
//                    suggestedElnNotebooksInfo.setNumExperiments(info.getNumExperiments());
//                }
//            }
//            return suggestedElnNotebooksInfo;
//        }
//    };
//
//    public AggregationResult<SuggestedExperimentsCard> aggregateResult(
//            SuggestedExperimentsCard newCard, SuggestedExperimentsCard aggregateCard) {
//        if (aggregateCard == null) {
//            return null;
//        }
//
//        AggregationResult<SuggestedExperimentsCard> aggregationResult = new AggregationResult<SuggestedExperimentsCard>();
//        aggregationResult.setIsAggregated(true);
//        SuggestedExperimentsInformation body = aggregateCard.getBody();
//        Float relevance = aggregateCard.getRelevance();
//        aggregateCard.setRelevance(Math.max(relevance, newCard.getRelevance()));
//        aggregateCard.setBody(new SuggestedExperimentsInformation(merge(
//                body.getExperiments(), newCard.getBody().getExperiments())));
//        aggregationResult.setAggregateCard(aggregateCard);
//
//        return aggregationResult;
//    }
//
//    private List<SuggestedExperimentInfo> merge(
//            List<SuggestedExperimentsCard.SuggestedExperimentInfo> list1,
//            List<SuggestedExperimentsCard.SuggestedExperimentInfo> list2) {
//        Multimap<String, SuggestedExperimentInfo> list1Index = Multimaps.index(
//                list1, EXTRACT_TITLE);
//        Multimap<String, SuggestedExperimentInfo> list2Index = Multimaps.index(
//                list2, EXTRACT_TITLE);
//        ArrayListMultimap<String, SuggestedExperimentInfo> index = ArrayListMultimap
//                .create(list1Index);
//        index.putAll(list2Index);
//
//        return Lists.newArrayList(Maps.transformValues(index.asMap(),
//                MERGE_SUGGINFO).values());
//    }

}